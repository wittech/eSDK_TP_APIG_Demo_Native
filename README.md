# eSDK_TP_APIG_Demo_Native
esdk_tp_config.properties 属性配置文件说明

#eSDK Service URL, for example : sdkserver.url = http://10.135.42.63:8086/esdk/services
#sdkserver.url = https://192.160.54.182:8343/esdk/services
#sdkserver.url = https://128.105.12.130:8343/esdk/services    

sdkserver.url 是APIG 对外发布的服务地址，请在APIG网关上查看
sdkserver.url = https://192.160.54.221:8343/esdk/services
#sdkserver.url = https://128.105.14.229:8343/esdk/services

#Sensitive information transmission mode in network, supported values: AES128_consulted, RSA2048, AES128_fixed, Plaintext
sensitive.information.transmission.mode = Plaintext

#to turn on/off the CXF logging interceptor
#true - turn on the logging interceptor, the logging will be output to console
#false - turn off the logging interceptor
cxf.logging.interceptor.on = true

SMC上注册的具备第三方调用权限的账号
#login user name
userName =
SMC上注册的具备第三方调用权限的账号密码
#login user password
password =
