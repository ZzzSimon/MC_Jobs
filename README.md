# MC_Jobs
A MineCraft Plugin
#氏族系统使用说明
选种族：***一人只能选一次，所以请慎重选择
vampire 吸血鬼 顾名思义 等级越高，吸血能力越强
giant 巨人族 每一级添加血量 攻击时有几率触发锋利效果 等级越高时间越长
human 右击能治疗玩家 等级越高 治疗能力越强 
指令：/jobs choose [vampire/giant/human]


#氏族插件更新计划——1.1.2
加入氏族缺陷：
vampire 在白天时 无法吸血
giant 身为巨人氏族，移速那得慢一些
human 身为治疗职业 本身的血量较少

#氏族插件更新计划——2.1.4
关于新等级制度：氏族邪能
喝了氏族邪能可以增长一个等级
来源：邪能感染者必掉一个出来 商店500买
每20级升级一个称号

#氏族插件更新计划——V2.2.4
加入妖族 ./jobs monster
能力：攻击时有几率 能让敌人 【中毒+凋零】 时间随等级提高
攻击时有几率 能让敌人 【减速 反胃 失明 虚弱】 其中一种 debuff
缺陷：触发过能力之后 自身伤害降低
人族加强
新增能力：1.右击【怪物】有几率致命一击 几率与伤害随等级提高
2.手中拿着羽毛对玩家右击加 【力量buff】时间随等级

#氏族插件更新计划——V2.2.5
加入信息查询： /jobs see
加入氏族转换： /jobs change [氏族名称]
修复101级bug

#氏族更新——V3.0.0(latest)
转职指令 jobs transfer 氏族名+_1或者_2 
列如 我原来是巨人，想转战神 就输入 /jobs transfer giant_1
巨人//1战神：攻击时触发力量效果，时间与效果等级随一转等级增加
//2泰坦：攻击时触发防御效果，时间与效果等级随一转等级增加
吸血鬼
//主动技能：（用羽毛触发）最大血量+lv*0.5持续5秒 cd 60s 
//1不死吸血鬼：白天可以吸血，但是白天与夜晚的吸血量都减少
//2暗影吸血鬼：夜间吸血能力翻倍，阳光下攻击别人时持续虚弱
人族：右键治疗队友，概率会心一击，血量101剑宗：会心伤害增加，与一转等级有关
2气宗：血量20，治疗量增加，与一转等级有关
//妖族：攻击概率触发中毒和凋零，概率触发减速，虚弱，失明反胃中的一（时间与初级等级有关，效果等级与一转等级有关） //1魅妖：攻击debuff概率翻倍
//2狐妖：攻击附带真实伤害2～10，每3s只能触发一次
