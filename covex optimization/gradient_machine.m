function [ gd_w ] = gradient_mchine(x,y,w)
    %input:x,y, a,b(given by server)
    %get gradient
    %output:
    %gd_x,gd_y
    gd_w=x'*(y-x*w);
end