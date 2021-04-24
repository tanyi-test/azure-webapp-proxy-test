package com.test.app;

import com.azure.core.credential.TokenCredential;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.Region;
import com.azure.core.management.profile.AzureProfile;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.resourcemanager.AzureResourceManager;
import com.azure.resourcemanager.appservice.models.PricingTier;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws Exception {
        String resourceGroupName = "resourceGroup";
        String webappName = "webapp";
        String appServicePlanName = "appServicePlan";
        PricingTier pricingTier = PricingTier.FREE_F1;
        Region region = Region.ASIA_EAST;

        TokenCredential credential = new DefaultAzureCredentialBuilder().build();
        AzureProfile profile = new AzureProfile(AzureEnvironment.AZURE);

        AzureResourceManager azure = AzureResourceManager.authenticate(credential, profile)
                .withDefaultSubscription();

        azure.webApps().define(webappName)
                .withRegion(region)
                .withNewResourceGroup(resourceGroupName)
                .withNewLinuxPlan(appServicePlanName, pricingTier)
                .withPublicDockerHubImage("chentanyi/simple-proxy")
                .create();
    }
}
