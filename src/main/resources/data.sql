
SET FOREIGN_KEY_CHECKS=0;
truncate table company;
truncate table ipo;
truncate table stock_price;
truncate table stock_exchange;
truncate table sector;
truncate table stock_company;
truncate table user_entity;
SET FOREIGN_KEY_CHECKS=1;

insert into sector value(10,"Banking","");
insert into sector value(11,"Technology","");
insert into sector value(12,"Telecommunication","");


insert into company values(1,"Google","20",2.5,"Sundar Pichai","Sundar Pichai","NSE","Technology","American multinational technology company",11);
insert into company values(2,"Microsoft","21",2.5,"Satya Nadella","v,j,y","NSE,BSE","Technology","American multinational technology company",11);
insert into company values(3,"SBI","500112",2.5,"Ashwini Kumar Tewari","v,j,y","","sector","State Bank of India",10);
insert into company values(4,"Ericsson","23",2.5,"Börje Ekholm","v,j,y","BSE","Telecommunication","Swedish multinational telecommunications company",12);
insert into company values(5,"Paytm","25",2.5,"Vijay Shekhar Sharma","v,j,y","BSE","Banking","Paytm is India's leading financial services company",10);

insert into stock_exchange value(101,"NSE","National Stock Exchange India","New Delhi","");
insert into stock_exchange value(102,"BSE","Bombay Stock Exchange","New Delhi","");

insert into stock_company values(101,1);
insert into stock_company values(101,2);
insert into stock_company values(102,2);
insert into stock_company values(102,4);


insert into ipo value(40,"Google","NSE",5.0,10,sysdate(),"jff",1);
insert into ipo value(41,"SBI","NSE",5.0,10,sysdate(),"jff",3);
insert into ipo value(42,"Paytm","NSE",5.0,10,sysdate(),"jff",5);
insert into ipo value(43,"Google","NSE",5.0,10,sysdate(),"jff",1);


