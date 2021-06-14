use trent;
drop table if exists item_spu;
create table item_spu(
id bigint,
title varchar (100) COMMENT '商品标题',
seller_id bigint COMMENT '商户ID',
pic varchar (20) COMMENT '商品图片的地址',
brand_id bigint COMMENT '品牌id',
firm_id bigint COMMENT '生产厂商id',
bg_third_category_id bigint COMMENT '后台三级类目id',
spu_status integer COMMENT '商品状态',
gmt_created datetime COMMENT '记录创建时间',
gmt_modified datetime COMMENT '记录修改时间',
version integer COMMENT '用于乐观锁'
) COMMENT 'SPU（标准商品单元）表';

drop table if exists item_sku;
create table item_sku(
id bigint COMMENT '',
spu_id bigint COMMENT '',
spec varchar (50) COMMENT '规格',
price bigint COMMENT '价格',
sku_status integer COMMENT 'sku状态',
gmt_created datetime COMMENT '记录创建时间',
gmt_modified datetime COMMENT '记录修改时间',
version integer COMMENT '用于乐观锁'
) COMMENT 'SKU（标准库存单元）表';

drop table if exists item_sku_stock;
create table sku_store_stock(
id bigint,
sku_id bigint COMMENT 'sku_id',
store_id bigint COMMENT '门店（仓库）id',
total_stock bigint COMMENT '库存总量',
gmt_created datetime COMMENT '记录创建时间',
gmt_modified datetime COMMENT '记录修改时间',
version integer COMMENT '用于乐观锁'
) COMMENT '库存表'

drop table if exists trade_order;
create table trade_order(
id bigint,
user_id bigint COMMENT '下单用户id',
order_time datetime COMMENT '下单时间',
order_status integer COMMENT '订单状态',
order_total_fee bigint COMMENT '订单总金额',
order_item_total_count integer COMMENT '订单商品总数',
gmt_created datetime COMMENT '记录创建时间',
gmt_modified datetime COMMENT '记录修改时间',
version integer COMMENT '用于乐观锁'
) COMMENT '订单表';

drop table if exists trade_item_detail;
create table trade_order_item_detail(
id bigint,
trade_order_id bigint COMMENT '订单号',
sku_id bigint COMMENT 'sku_id',
order_sku_count integer COMMENT '订单sku数量',
unit_price bigint COMMENT 'sku单价',
total_fee bigint COMMENT '总金额',
total_cash bigint COMMENT '现金总额',
total_discount_amount bigint_COMMENT '折扣金额',
logistic_id bigint COMMENT '物流id',
gmt_created datetime COMMENT '记录创建时间',
gmt_modified datetime COMMENT '记录修改时间',
version integer COMMENT '用于乐观锁'
)


drop table if exists `user`;
create table `user`(
id bigint,
gender tinyint COMMENT '性别',
birth_date datetime COMMENT '生日',
cell_phone_num varchar(50) COMMENT '手机号码',
gmt_created datetime COMMENT '记录创建时间',
gmt_modified datetime COMMENT '记录修改时间',
version integer COMMENT '用于乐观锁'
);