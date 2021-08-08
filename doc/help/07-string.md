**检查字符串不为空**

```
string.isNotNullOrEmpty()
```

**验证电话号码**

```
string.isValidPhone()
```

**验证邮箱**

```
string.isValidEmail()
```

**验证 IP 地址**

```
string.isIPAddress()
```

**验证域名**

```
string.isDomainName()
```

**格式化手机号**

```
string.formatPhoneNumber()
```

**md5加密**

```
string.md5()
```

**默认生成不包含 null 值的 JSON 串**

```
PeopleModel().toJson()

output: {"name":"dhl"}
```


**生成包含 null 值的 JSON 串**

通常用于 API 接口中没有值默认使用 null

```
PeopleModel("dhl").toJson(true)

output: {"address":null,"name":"dhl"}
```

**解析 JSON**

```
json.fromJson<PeopleModel>()
```



