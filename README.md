# two-factor-auth-sample
Sample project for two factor auth implementation on Java.

## How to execute?
1. Build with Maven and execute built jar.
```bash
 cd target
 java -jar two-factor-auth-sample-1.0-SNAPSHOT-jar-with-dependencies.jar
```

2. Enter the following URL with browser. And then a QRCode is shown on your screen.
```
http://localhost:8080/myresource/credential?userName=<your name>
```

3. Read the QRCode with Google Authenticator.

4. Enter the following URL with browser to execute authentication.
```
http://localhost:8080/myresource/auth?userName=<yur name>&code=<the code generated by Google Authenticator>
```

## dependencies
This sample project uses [wstrange/GoogleAuth](https://github.com/wstrange/GoogleAuth) that is multi-factor authentication  library for a Java application.