
SET FOREIGN_KEY_CHECKS=0;
truncate table company;
truncate table ipo;
truncate table stock_price;
truncate table stock_exchange;
truncate table sector;
truncate table stock_company;
SET FOREIGN_KEY_CHECKS=1;

insert into sector value(10,"Banking","rjkrg");
insert into sector value(11,"Technology","kfmrld");
insert into sector value(12,"Telecommunication","kfmrld");


insert into company values(1,"Google","20",2.5,"vgh","v,j,y","NSE","Technology","hbjjbvg",11);
insert into company values(2,"Microsoft","21",2.5,"vgh","v,j,y","NSE,BSE","Technology","hbjjbvg",11);
insert into company values(3,"SBI","500112",2.5,"vgh","v,j,y","","sector","Banking",10);
insert into company values(4,"Ericsson","23",2.5,"vgh","v,j,y","BSE","Telecommunication","hbjjbvg",12);
insert into company values(5,"SocGen","25",2.5,"vgh","v,j,y","BSE","Banking","hbjjbvg",10);

insert into stock_exchange value(101,"NSE","jvnfk","djen","efje");
insert into stock_exchange value(102,"BSE","jvnfk","djen","efje");

insert into stock_company values(101,1);
insert into stock_company values(101,2);
insert into stock_company values(102,2);
insert into stock_company values(102,4);


insert into ipo value(40,"Google","NSE",5.0,10,sysdate(),"jff",1);
insert into ipo value(41,"SBI","NSE",5.0,10,sysdate(),"jff",3);
insert into ipo value(42,"SocGen","NSE",5.0,10,sysdate(),"jff",5);
insert into ipo value(43,"Google","NSE",5.0,10,sysdate(),"jff",1);


insert into stock_price value(30,"20","NSE",3.4,sysdate(),sysdate(),1);
insert into stock_price value(31,"20","NSE",3.4,sysdate(),sysdate(),1);
insert into stock_price value(32,"21","NSE",3.4,sysdate(),sysdate(),2);




