import { HttpHeaders } from '@angular/common/http';
export function  myheader(){
    let headers= new HttpHeaders();
    if(localStorage.getItem('accessToken')!=null){
       
        headers=headers.set('Authorization','Bearer '+localStorage.getItem('accessToken'))
    }
    
    return headers;
   
}