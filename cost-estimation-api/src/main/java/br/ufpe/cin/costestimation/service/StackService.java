package br.ufpe.cin.costestimation.service;

import br.ufpe.cin.costestimation.client.CloudFormationClientImpl;
import br.ufpe.cin.costestimation.client.DevOpsGuruClientImpl;
import br.ufpe.cin.costestimation.exception.CostEstimationAlreadyExistException;
import br.ufpe.cin.costestimation.exception.CostEstimationNotFoundException;
import br.ufpe.cin.costestimation.mapper.MapperStack;
import br.ufpe.cin.costestimation.model.Stack;
import br.ufpe.cin.costestimation.model.StatusType;
import br.ufpe.cin.costestimation.repository.Stacks;
import br.ufpe.cin.costestimation.vo.StackRequestVO;
import br.ufpe.cin.costestimation.vo.StackResponseVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StackService {

    private final Stacks stacks;

    private final CloudFormationClientImpl cloudFormationClientImpl;

    private final DevOpsGuruClientImpl devOpsGuruClientImpl;

    private final MapperStack mapperStack;

    public StackService(final Stacks stacks, final CloudFormationClientImpl cloudFormationClientImpl,
                        final MapperStack mapperStack, final DevOpsGuruClientImpl devOpsGuruClientImpl) {
        this.stacks = stacks;
        this.cloudFormationClientImpl = cloudFormationClientImpl;
        this.devOpsGuruClientImpl = devOpsGuruClientImpl;
        this.mapperStack = mapperStack;
    }

    public void sendStackCloudFormation() {
        List<Stack> stacksExists = stacks.findByType(StatusType.NOT_SENT);

        if (CollectionUtils.isNotEmpty(stacksExists)) {

            stacksExists.forEach(stack -> {
                final String stackId = cloudFormationClientImpl.createStack(StackRequestVO.builder()
                        .name(stack.getName())
                        .templateBody(stack.getTemplateBody())
                        .build());

                stack.setType(StatusType.SENT);
                stack.setStackId(stackId);
            });

            stacks.saveAll(stacksExists);
        } else {
            System.out.println("Nao existe stacks para enviar ao CloudFormation.");
        }
    }

    public void startCostEstimationDevOpsGuru() {
        List<Stack> stacksExists = stacks.findByType(StatusType.SENT);

        if (CollectionUtils.isNotEmpty(stacksExists)) {
            stacksExists.forEach(stack -> {

                List<String> stackNames = stacksExists
                        .stream()
                        .map(Stack::getName)
                        .collect(Collectors.toList());

                devOpsGuruClientImpl.startCostEstimation(stackNames);

                stack.setType(StatusType.START_COST_ESTIMATION);
            });

            stacks.saveAll(stacksExists);
        } else {
            System.out.println("Nao existe stacks para iniciar a estimativa de custos no DevOpsGuru.");
        }
    }

    public void getCostEstimationDevOpsGuru() {

        devOpsGuruClientImpl.getCostEstimation();

//        List<Stack> stacksExists = stacks.findByType(StatusType.START_COST_ESTIMATION);
//
//        if (CollectionUtils.isNotEmpty(stacksExists)) {
//            devOpsGuruClientImpl
//        }
    }

    public StackResponseVO createStack(final StackRequestVO requestVO) {

        final Optional<Stack> stackExists = stacks.findByName(requestVO.getName());

        if (stackExists.isPresent()) {
            throw new CostEstimationAlreadyExistException();
        }

        Stack stackNew = mapperStack.stackRequestVOtoStack(requestVO);

        stackNew = stacks.save(stackNew);

        final StackResponseVO responseVO = mapperStack.stackToStackResponseVO(stackNew);

        return responseVO;
    }

    public StackResponseVO findStackByName(final String name) {

        final Optional<Stack> stack = stacks.findByName(name);

        if (!stack.isPresent()) {
            throw new CostEstimationNotFoundException();
        }

        final StackResponseVO responseVO = mapperStack.stackToStackResponseVO(stack.get());

        return responseVO;
    }

    public void delete(final String name) {

        final Optional<Stack> stack = stacks.findByName(name);

        if (!stack.isPresent()) {
            throw new CostEstimationNotFoundException();
        }

        stacks.delete(stack.get());
    }

}
