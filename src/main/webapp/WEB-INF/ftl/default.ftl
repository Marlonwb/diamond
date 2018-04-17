<!doctype html>
<#import "spring.ftl" as spring/>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title><@spring.message "project_name"/></title>
    <script src="/static/js/jquery-1.11.1.min.js"></script><!-- jquery-->
</head>
<body>
hello world! <@spring.message "author"/> <br/>
${appDomain}
</body>
</html>