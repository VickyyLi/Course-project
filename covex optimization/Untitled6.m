% data  
x = [0 10 20 30 40 50 60 70 80];  
y = [15 18 22 27 29 34 40 48 55];  
  
theta1 = 0;     % һ��ϵ��  
theta0 = 0;     % ��ϵ��  
  
v = 0.00001;    % ���� Ԫ�ظ���С��9��0.0001Ҳ��  
  
[row,col] = size(x);  
  
% theta��ʼ��  ��ֵѡȡ�Խ��Ӱ��Ҳ�ܴ� �ر���theta2��ѡȡ  
theta1 = mean(y) / mean(x);  
theta0 = y(1) - theta1 * x(1);  
  
 while 1  
     temp1 = 0;  
     temp0 = 0;  
     for i=1:col  
         temp1 = temp1 + ((y(i) - (theta1*x(i) + theta0)) * x(i))
         temp0 = temp0 + ((y(i) - (theta1*x(i) + theta0)) * 1);  
     end  
     temp=temp1
     old_theta1 = theta1;  
     old_theta0 = theta0;    
     theta1 = theta1 + v * temp1 ;
     theta0 = theta0 + v * temp0 ;
     temp1 = 0;      
     temp0 = 0;  
       
     % ���  
     e = ((old_theta1 - theta1)^2 + (old_theta0 - theta0)^2);  
       
     if  e < 0.000003         
         f = theta1 * x + theta0;  
         plot(x,y,'r+',x,f);  
         break;  
     end  
 end