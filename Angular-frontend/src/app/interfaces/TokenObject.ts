

export interface TokenObject{
    sub:string;
    userId:string;
    role:Array<Authorities>;
    iat:bigint;
    exp:bigint;
  }
export  interface Authorities{
    authority:string;
  }