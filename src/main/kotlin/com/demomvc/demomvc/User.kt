package com.demomvc.demomvc

class User constructor(){
    var id:Int=0
    var user:String="";
    var pass:String="";
    constructor(id:Int,user:String,pass:String):this(){
        this.id=id;
        this.user=user;
        this.pass=pass;
    }

    override fun toString(): String {
        return "User{" +
                "user= $user"  +
                ", pass= $pass" +
                "}"

    }
}