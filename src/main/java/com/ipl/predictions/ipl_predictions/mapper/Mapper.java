package com.ipl.predictions.ipl_predictions.mapper;

public interface Mapper<A,B> {

    B mapTo(A a);

    A mapFrom(B b);


}
