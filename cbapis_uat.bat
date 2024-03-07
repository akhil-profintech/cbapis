//./gradlew build ====>alternative command
call ./gradlew bootJar

call ./gradlew bootBuildImage

call docker build --build-arg JAR_FILE=build/libs/cbapis-latest.jar -t cbapis-uat .

call aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 220474796007.dkr.ecr.ap-south-1.amazonaws.com

call kubectl delete deployment cbapis-uat -n cbapis-uat

call kubectl delete service cbapis-service-uat -n cbapis-uat

call kubectl delete service cbapis-loadbalancer-uat -n cbapis-uat

call docker tag cbapis-uat:latest 220474796007.dkr.ecr.ap-south-1.amazonaws.com/cbapis-uat:latest

call docker push 220474796007.dkr.ecr.ap-south-1.amazonaws.com/cbapis-uat:latest

call aws eks --region ap-south-1 update-kubeconfig --name ProCluster-UAT

call kubectl config get-contexts

kubectl create ns cbapis-uat

call kubectl apply -f .\deployment_uat.yaml -n cbapis-uat

call kubectl apply -f .\service_uat.yaml -n cbapis-uat

call kubectl apply -f .\loadbalancer_uat.yaml -n cbapis-uat

call kubectl get deployments -n cbapis-uat

call kubectl get services -n cbapis-uat

call kubectl get pods -n cbapis-uat


call kubectl get service cbapis-loadbalancer-uat -n cbapis-uat  -o jsonpath='{.spec.clusterIP}'
