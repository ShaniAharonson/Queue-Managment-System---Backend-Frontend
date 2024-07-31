export class UserDetails {
    public userId: number;
    public email: string;
    public password: string;
    public userName: string;
    public userType: string;

   
    constructor(userId: number, email: string, password: string, userName: string, userType:string) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.userType = userType;
    }
}