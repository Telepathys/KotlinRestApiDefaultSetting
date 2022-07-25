# B2cBackendKotlinDefault-Setting
## Run

### Locally


```bash
gradle bootRun
```

### Build and push the image. Using gradle jib
```bash
# Install docker in advance

aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin <account_id>.dkr.ecr.us-west-2.amazonaws.com

gradle jib
```

### Troubleshootings

1.
```
# Run gradle with java 11
gradle bootRun -Dorg.gradle.java.home=/Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home
```

### Competitors
1. https://developers.pandascore.co/docs