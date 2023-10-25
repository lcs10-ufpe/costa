package br.ufpe.cin.costestimation.client;

import br.ufpe.cin.costestimation.vo.StackRequestVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.cloudformation.CloudFormationClient;
import software.amazon.awssdk.services.cloudformation.model.CloudFormationException;
import software.amazon.awssdk.services.cloudformation.model.CreateStackRequest;
import software.amazon.awssdk.services.cloudformation.model.CreateStackResponse;
import software.amazon.awssdk.services.cloudformation.model.OnFailure;

import java.io.IOException;

@Component
public class CloudFormationClientImpl {

    private final CloudFormationClient cloudFormationClient;

    public CloudFormationClientImpl(final CloudFormationClient cloudFormationClient) {
        this.cloudFormationClient = cloudFormationClient;
    }

    public String createStack(final StackRequestVO requestVO) {

        String stackId;

        try {

            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

            String jsonTemplateBody = mapper.writeValueAsString(mapper.readTree(requestVO.getTemplateBody()));

            CreateStackRequest stackRequest = CreateStackRequest.builder()
                    .stackName(requestVO.getName())
                    .onFailure(OnFailure.ROLLBACK)
                    .templateBody(jsonTemplateBody)
                    .build();

            final CreateStackResponse stackResponse =  cloudFormationClient.createStack(stackRequest);

            stackId = stackResponse.stackId();
        } catch (IOException ex) {
            stackId = "Algum erro aconteceu";
            System.err.println(ex.getMessage());
            System.exit(1);
        } catch (final CloudFormationException e) {
            stackId = "Algum erro aconteceu";
            System.err.println(e.getMessage());
            System.exit(1);
        }

        return stackId;
    }

}
