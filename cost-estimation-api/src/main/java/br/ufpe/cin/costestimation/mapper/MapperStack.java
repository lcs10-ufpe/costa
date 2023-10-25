package br.ufpe.cin.costestimation.mapper;

import br.ufpe.cin.costestimation.model.Stack;
import br.ufpe.cin.costestimation.model.StatusType;
import br.ufpe.cin.costestimation.vo.StackRequestVO;
import br.ufpe.cin.costestimation.vo.StackResponseVO;
import org.springframework.stereotype.Component;

@Component
public class MapperStack {

    public Stack stackRequestVOtoStack(final StackRequestVO requestVO) {
        final Stack stack = Stack.builder()
                .name(requestVO.getName())
                .templateBody(requestVO.getTemplateBody())
                .type(StatusType.NOT_SENT)
                .build();

        return stack;
    }

    public StackResponseVO stackToStackResponseVO(final Stack stack) {
        final StackResponseVO responseVO = StackResponseVO.builder()
                .name(stack.getName())
                .templateBody(stack.getTemplateBody())
                .type(stack.getType())
                .stackId(stack.getStackId())
                .build();

        return responseVO;
    }

}
