package br.ufpe.cin.executer.jenkins;

import br.ufpe.cin.executer.model.CloudProviderEnum;
import br.ufpe.cin.executer.vo.PlanMigrationVO;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JenkinsApiClientImpl implements JenkinsApiClient {

    private final JenkinsServer jenkins;

    public JenkinsApiClientImpl(final JenkinsServer jenkins) throws MalformedURLException {
        this.jenkins = jenkins;
    }

    public void executePlanning(final PlanMigrationVO planMigrationVO) throws IOException {
        executePipeline(getJenkinsPipelineName(planMigrationVO.getNameProject(), planMigrationVO.getDeploy()), "");
        // checkApplicationRunningInCloud(planMigrationVO.getNameProject(), planMigrationVO.getDeploy());
        // executePipeline(getJenkinsPipelineName(planMigrationVO.getNameProject(), planMigrationVO.getRemove()),
        // "--auto-approve");
    }

    public void firstDeploy(final String pipelineName) throws IOException {
        executePipeline(pipelineName, "");
    }

    public void remove(final String pipelineName) throws IOException {
        executePipeline(pipelineName, "--auto-approve");
    }

    public void removeDeployScheduler(final String nameProject, final CloudProviderEnum removeCloud)
            throws IOException {
        executePipeline(getJenkinsPipelineName(nameProject, removeCloud), "--auto-approve");
    }

    private void executePipeline(final String pipelineName, final String param) throws IOException {
        final Map<String, Job> jobs = jenkins.getJobs();

        final JobWithDetails job = jobs.get(pipelineName).details();

        Map<String, String> build = new HashMap<>();
        build.put("APPROVE", param);
        job.build(build);
    }

    private String getJenkinsPipelineName(final String nameProject, final CloudProviderEnum cloud) {
        String name = null;

        if ("Beerstore".equals(nameProject)) {
            if (CloudProviderEnum.AMAZON_WEB_SERVICES.equals(cloud)) {
                name = "iac-demo";
            } else if (CloudProviderEnum.GOOGLE_CLOUD_PLATFORM.equals(cloud)) {
                name = "iac-demo-gcp";
            } else if (CloudProviderEnum.MICROSOFT_AZURE.equals(cloud)) {
                name = "iac-demo-az";
            }
        }

        if ("Hipster-Shop".equals(nameProject)) {
            if (CloudProviderEnum.AMAZON_WEB_SERVICES.equals(cloud)) {
                name = "hipster-shop-aws";
            } else if (CloudProviderEnum.GOOGLE_CLOUD_PLATFORM.equals(cloud)) {
                name = "hipster-shop-gcp";
            } else if (CloudProviderEnum.MICROSOFT_AZURE.equals(cloud)) {
                name = "hipster-shop-azure";
            }
        }

        if ("Sock-Shop".equals(nameProject)) {
            if (CloudProviderEnum.AMAZON_WEB_SERVICES.equals(cloud)) {
                name = "sock-shop-aws";
            } else if (CloudProviderEnum.GOOGLE_CLOUD_PLATFORM.equals(cloud)) {
                name = "sock-shop-gcp";
            } else if (CloudProviderEnum.MICROSOFT_AZURE.equals(cloud)) {
                name = "sock-shop-azure";
            }
        }

        return name;
    }

//    private void checkApplicationRunningInCloud(final String nameProject, final CloudProviderEnum cloud) {
//        String application = null;
//        long sleep = 0;
//
//        if ("Beerstore".equals(nameProject)) {
//            sleep = 240000;
//            if (CloudProviderEnum.AMAZON_WEB_SERVICES.equals(cloud)) {
//                application = "http://3.230.104.191:8080/swagger-ui.html";
//            } else if (CloudProviderEnum.GOOGLE_CLOUD_PLATFORM.equals(cloud)) {
//                application = "http://34.72.122.128:8080/swagger-ui.html";
//            } else if (CloudProviderEnum.MICROSOFT_AZURE.equals(cloud)) {
//                application = "http://40.76.63.255:8080/swagger-ui.html";
//            }
//        }
//
//        if ("Hipster-Shop".equals(nameProject)) {
//            sleep = 300000;
//            if (CloudProviderEnum.AMAZON_WEB_SERVICES.equals(cloud)) {
//                application = "http://52.22.158.31:30274/";
//            } else if (CloudProviderEnum.GOOGLE_CLOUD_PLATFORM.equals(cloud)) {
//                application = "http://34.71.217.142:30274/";
//            } else if (CloudProviderEnum.MICROSOFT_AZURE.equals(cloud)) {
//                application = "http://40.121.91.93:30274/";
//            }
//        }
//
//        if ("Sock-Shop".equals(nameProject)) {
//            sleep = 240000;
//            if (CloudProviderEnum.AMAZON_WEB_SERVICES.equals(cloud)) {
//                application = "http://3.219.160.11/";
//            } else if (CloudProviderEnum.GOOGLE_CLOUD_PLATFORM.equals(cloud)) {
//                application = "http://34.66.194.254/";
//            } else if (CloudProviderEnum.MICROSOFT_AZURE.equals(cloud)) {
//                application = "http://40.85.173.48/";
//            }
//        }
//
//        isApplicationStarted(application, sleep);
//    }

    // private void isApplicationStarted(final String application, final long sleep) {
    // try {
    // int code = 0;
    // URL url = new URL(application);
    // do {
    // Thread.sleep(sleep);
    //
    // HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    //
    // code = connection.getResponseCode();
    //
    // if (code == 200) {
    // System.out.println("200 OK!!!!");
    // }
    //
    // } while (code != 200);
    //
    // } catch (MalformedURLException e) {
    // e.printStackTrace();
    // } catch (IOException e) {
    // isApplicationStarted(application, sleep);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }

}
