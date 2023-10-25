package br.ufpe.cin.executer.service;

import br.ufpe.cin.executer.jenkins.JenkinsApiClient;
import br.ufpe.cin.executer.model.CloudProviderEnum;
import br.ufpe.cin.executer.model.RemoveDeployEntity;
import br.ufpe.cin.executer.model.RunningEnum;
import br.ufpe.cin.executer.repository.RemoveDeploys;
import br.ufpe.cin.executer.shell.LocalShell;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RemoveDeployService {

    private final RemoveDeploys removeDeploys;

    private final LocalShell localShell;

    private final JenkinsApiClient jenkinsApiClient;

    public RemoveDeployService(final RemoveDeploys removeDeploys, final LocalShell localShell,
            final JenkinsApiClient jenkinsApiClient) {
        this.removeDeploys = removeDeploys;
        this.localShell = localShell;
        this.jenkinsApiClient = jenkinsApiClient;
    }

    public void save(final RemoveDeployEntity removeDeployEntity) {
        this.removeDeploys.save(removeDeployEntity);
    }

    public void executeRemoveDeploy() throws IOException {
        Optional<RemoveDeployEntity> removeDeployEntity = removeDeploys.findByRunning(RunningEnum.YES);

        if (removeDeployEntity.isPresent()) {

            RemoveDeployEntity removeDeploy = removeDeployEntity.get();

            final String application = checkApplicationRunningInCloud(removeDeploy.getNameProject(),
                    removeDeploy.getDeploy());
            boolean isStarted = isApplicationStarted(application);

            if (isStarted) {
                final String down = getIpInstanceInCloud(removeDeploy.getNameProject(), removeDeploy.getRemove());
                final String up = getIpInstanceInCloud(removeDeploy.getNameProject(), removeDeploy.getDeploy());
                localShell.executeCommand(down, up);

                removeDeploy.setRunning(RunningEnum.NO);
                removeDeploys.save(removeDeploy);

                jenkinsApiClient.removeDeployScheduler(removeDeploy.getNameProject(), removeDeploy.getRemove());

                log.info("Aplicacao " + removeDeployEntity.get().getNameProject() + " saiu da nuvem "
                        + removeDeploy.getRemove());
                log.info("Aplicacao " + removeDeployEntity.get().getNameProject() + " esta na nuvem "
                        + removeDeploy.getDeploy());
            } else {
                log.info("Aplicacao " + removeDeployEntity.get().getNameProject() + " ainda nao subiu");
                log.info("na nuvem " + removeDeployEntity.get().getDeploy());
            }

        } else {
            log.info("Nao existe deploy ate o momento para remover");
        }

    }

    private String checkApplicationRunningInCloud(final String nameProject, final CloudProviderEnum cloud) {
        String application = null;

        if ("Beerstore".equals(nameProject)) {
            if (CloudProviderEnum.AMAZON_WEB_SERVICES.equals(cloud)) {
                application = "http://" + getIpInstanceInCloud(nameProject, cloud) + "/swagger-ui.html";
            } else if (CloudProviderEnum.GOOGLE_CLOUD_PLATFORM.equals(cloud)) {
                application = "http://" + getIpInstanceInCloud(nameProject, cloud) + "/swagger-ui.html";
            } else if (CloudProviderEnum.MICROSOFT_AZURE.equals(cloud)) {
                application = "http://" + getIpInstanceInCloud(nameProject, cloud) + "/swagger-ui.html";
            }
        }

        if ("Hipster-Shop".equals(nameProject)) {
            if (CloudProviderEnum.AMAZON_WEB_SERVICES.equals(cloud)) {
                application = "http://" + getIpInstanceInCloud(nameProject, cloud) + "/";
            } else if (CloudProviderEnum.GOOGLE_CLOUD_PLATFORM.equals(cloud)) {
                application = "http://" + getIpInstanceInCloud(nameProject, cloud) + "/";
            } else if (CloudProviderEnum.MICROSOFT_AZURE.equals(cloud)) {
                application = "http://" + getIpInstanceInCloud(nameProject, cloud) + "/";
            }
        }

        if ("Sock-Shop".equals(nameProject)) {
            if (CloudProviderEnum.AMAZON_WEB_SERVICES.equals(cloud)) {
                application = "http://" + getIpInstanceInCloud(nameProject, cloud) + "/";
            } else if (CloudProviderEnum.GOOGLE_CLOUD_PLATFORM.equals(cloud)) {
                application = "http://" + getIpInstanceInCloud(nameProject, cloud) + "/";
            } else if (CloudProviderEnum.MICROSOFT_AZURE.equals(cloud)) {
                application = "http://" + getIpInstanceInCloud(nameProject, cloud) + "/";
            }
        }

        return application;
    }

    private boolean isApplicationStarted(final String application) {
        boolean isRunning = false;

        try {

            URL url = new URL(application);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int code = connection.getResponseCode();

            if (code == 200) {
                System.out.println("200 OK!!!!");
                isRunning = true;
            } else {
                System.out.println("Ainda nao subiu!!!!");
                System.out.println("Status code " + code);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            log.info("Aplicacao ainda nao esta respondendo. O deploy ainda esta acontecendo.");
        }

        return isRunning;
    }

    private String getIpInstanceInCloud(final String nameProject, final CloudProviderEnum cloud) {
        String ip = null;

        if ("Beerstore".equals(nameProject)) {
            if (CloudProviderEnum.AMAZON_WEB_SERVICES.equals(cloud)) {
                ip = "3.230.104.191:8080";
            } else if (CloudProviderEnum.GOOGLE_CLOUD_PLATFORM.equals(cloud)) {
                ip = "34.72.122.128:8080";
            } else if (CloudProviderEnum.MICROSOFT_AZURE.equals(cloud)) {
                ip = "40.76.63.255:8080";
            }
        }

        if ("Hipster-Shop".equals(nameProject)) {
            if (CloudProviderEnum.AMAZON_WEB_SERVICES.equals(cloud)) {
                ip = "52.22.158.31:30274";
            } else if (CloudProviderEnum.GOOGLE_CLOUD_PLATFORM.equals(cloud)) {
                ip = "34.71.217.142:30274";
            } else if (CloudProviderEnum.MICROSOFT_AZURE.equals(cloud)) {
                ip = "40.121.91.93:30274";
            }
        }

        if ("Sock-Shop".equals(nameProject)) {
            if (CloudProviderEnum.AMAZON_WEB_SERVICES.equals(cloud)) {
                ip = "3.219.160.11";
            } else if (CloudProviderEnum.GOOGLE_CLOUD_PLATFORM.equals(cloud)) {
                ip = "34.66.194.254";
            } else if (CloudProviderEnum.MICROSOFT_AZURE.equals(cloud)) {
                ip = "40.85.173.48";
            }
        }

        return ip;
    }

}
