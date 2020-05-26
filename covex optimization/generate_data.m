function [ data_x,data_y ] = generate_data(num_machine,byzantine_num,num_of_data,truth)
    %input:num_machine,num_iterations
    %random generate data for machines with mean=0,[0,100]
    %output:
    %data_x,data_y
    %--------------------------------------
    for i=1:(num_machine-byzantine_num)
        data_x{i}(:,1)=ones(num_of_data,1);
        data_x{i}(:,2)=100*rand(num_of_data,1);
        data_y{i}=data_x{i}*truth;
    end
    for i=(num_machine-byzantine_num+1):num_machine
        data_x{i}(:,1)=ones(num_of_data,1);
        data_x{i}(:,2)=100*rand(num_of_data,1);
        data_y{i}=100*rand(num_of_data,1);
    end
end

