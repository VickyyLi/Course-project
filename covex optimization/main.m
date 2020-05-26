%two method: standard gadient descent && byzantine gradient decent
%config:
%number of machine:12
%number of iteeration:100
%linear regression:Ax+b
%truth a:3
%truth b:4
%-----------generte_data && config------------------
number_of_machine=12;
batch=4;
number_of_byzantine=1;
iteration=80;
truth_a = 3;
truth_b = 0.5;
truth = [truth_b truth_a]';
num_of_data=40;
[data_x,data_y] = generate_data(number_of_machine,number_of_byzantine,num_of_data,truth);
initial_a = 2;
initial_b = 0;
initial_w = [initial_b initial_a]';
step_size=0.000001;
%----------run_GD------------
w=initial_w;
for k = 1:iteration
    sum_w=0;
    for machine = 1:number_of_machine
        [gd_w] = gradient_machine(data_x{machine},data_y{machine},w);
        sum_w=sum_w+gd_w;
    end
    sum_gd_w=sum_w/number_of_machine;
    w = w+step_size*sum_gd_w;
    error_w(1,k)=0;
    for j=1:(number_of_machine-1)
        error_w(1,k)=0.5*norm(data_y{j}-data_x{j}*w)^2;
    end
    error_w(1,k)=error_w(1,k)/number_of_machine;
end
iterations=1:iteration;
loglog(iterations,error_w);
hold on;
%-----------generte_data && config------------------
w=initial_w;
place=number_of_machine/batch;
error_w=zeros(1,iteration);
total_gd_w=zeros(2,batch);
for k = 1:iteration
    sum_w=0;
    sum_gd_w=0;
    for machine = 1:number_of_machine
        [gd_w] = gradient_machine(data_x{machine},data_y{machine},w);
        sum_w=sum_w+gd_w;
        if mod(machine,place)==0
            pt=machine/place;
            total_gd_w(:,pt)=sum_w(1)/batch;
            sum_w=0;
        end
    end
    
    cvx_begin quiet
    variable sum_gd_w
    minimize(norm(sum_gd_w-total_gd_w(:,1))+norm(sum_gd_w-total_gd_w(:,2))+norm(sum_gd_w-total_gd_w(:,3)))
	cvx_end

    w = w+step_size*sum_gd_w;
    error_w(1,k)=0;
    for j=1:(number_of_machine-1)
        error_w(1,k)=0.5*norm(data_y{j}-data_x{j}*w)^2;
    end
    error_w(1,k)=error_w(1,k)/number_of_machine;
    
    
    
end
iterations=1:iteration;
loglog(iterations,error_w);