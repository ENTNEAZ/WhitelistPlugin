# OurCraft 白名单插件

## 支持功能
- [x] 使用Mirai API 查询用户是否在群内
- [x] 未登记玩家可走动参观，处于冒险模式。且禁止交互
- [x] 登记玩家可正常游玩


## 使用方法
1. 安装插件，并启动一次获取配置文件
2. 配置Mirai API
3. 编写配置文件

```yaml
MiraiAddr: "http://127.0.0.1:5300/" # Mirai API地址
GroupQQID: "123456789" # 群号
```

4. 重启插件

## 命令列表
### 普通玩家命令
- `/reg <QQ>` 将自己的账号与QQ绑定
### 管理员命令
- `/whitelist add <UUID> <QQ>` 将UUID与QQ绑定
- `/whitelist remove <UUID>` 移除UUID绑定
- `/whitelist list` 列出所有绑定的UUID


## 配置文件/白名单文件
### config.yml
```yaml
MiraiAddr: "http://127.0.0.1:5300/" # Mirai API地址
GroupQQID: "123456789" # 群号
```

### whitelist.yml
```yaml
whitelist:
  - "UUIO:QQ"
  - "00000000-0000-0000-0000-000000000000:1111111111"
```