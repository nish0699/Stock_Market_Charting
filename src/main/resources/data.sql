
SET FOREIGN_KEY_CHECKS=0;
truncate table company;
truncate table ipo;
truncate table stock_price;
truncate table stock_exchange;
truncate table sector;
truncate table stock_company;
SET FOREIGN_KEY_CHECKS=1;

insert into sector value(10,"sect","kfmrl");
insert into sector value(11,"sector","kfmrld");

insert into company values(1,"A","20",2.5,"vgh","v,j,y","NSE","sect","hbjjbvg",10);
insert into company values(2,"AB","21",2.5,"vgh","v,j,y","NSE,BSE","sect","hbjjbvg",10);
insert into company values(3,"BAC","22",2.5,"vgh","v,j,y","","sector","hbjjbvg",11);
insert into company values(4,"D","23",2.5,"vgh","v,j,y","BSE","sector","hbjjbvg",11);
insert into company values(5,"E","25",2.5,"vgh","v,j,y","BSE","sector","hbjjbvg",11);

insert into stock_exchange value(101,"NSE","jvnfk","djen","efje");
insert into stock_exchange value(102,"BSE","jvnfk","djen","efje");

insert into stock_company values(101,1);
insert into stock_company values(101,2);
insert into stock_company values(102,2);
insert into stock_company values(102,4);


insert into ipo value(40,"A","NSE",5.0,10,sysdate(),"jff",1);
insert into ipo value(41,"A","NSE",5.0,10,sysdate(),"jff",1);
insert into ipo value(42,"AB","NSE",5.0,10,sysdate(),"jff",2);
insert into ipo value(43,"AB","NSE",5.0,10,sysdate(),"jff",2);


insert into stock_price value(30,"20","NSE",3.4,sysdate(),sysdate(),1);
insert into stock_price value(31,"20","NSE",3.4,sysdate(),sysdate(),1);
insert into stock_price value(32,"21","NSE",3.4,sysdate(),sysdate(),2);




