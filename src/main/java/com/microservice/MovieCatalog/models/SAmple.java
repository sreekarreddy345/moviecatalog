package com.microservice.MovieCatalog.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SAmple {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("sreekar");        stringList.add("sreekar1");
        stringList.add("sreeka2");        stringList.add("sreekar3");
        stringList.add("sreekar4");        stringList.add("sreekar5");
        stringList.add("sreekar6");        stringList.add("sreekar7");
        stringList.add("sreekar8");        stringList.add("sreekar9");
        stringList.add("sreekar10");

        int sreekar9 = stringList.indexOf("sreekar9");
        System.out.println("sreekar9 - " + sreekar9);

//        stringList.stream().forEach(name ->
//                System.out.println("Name - " + name)
//        );

        List<String> var = Arrays.asList("aa", "bb", "cc", "dd");
//        var.forEach(v ->
//                System.out.println("some variables - " + v));

    }
}
