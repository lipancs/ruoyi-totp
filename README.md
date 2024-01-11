# ruoyi-totp
集成totp功能
数据库用户表中新增tcode字段，varchar类型
在SysLoginService中扩展了validateTcode(username, tcode)验证方法
在SysProfileController中扩展了相应重置，生成，校验等基本功能

效果：
![Image text](https://raw.githubusercontent.com/lipancs/ruoyi-totp/main/1.png)
![Image text](https://raw.githubusercontent.com/lipancs/ruoyi-totp/main/2.png)
![Image text](https://raw.githubusercontent.com/lipancs/ruoyi-totp/main/3.png)