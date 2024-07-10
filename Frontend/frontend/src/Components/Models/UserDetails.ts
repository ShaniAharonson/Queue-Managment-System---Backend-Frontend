export class UserDetails {
    private userId: number;
    private email: string;
    private password: string;

    constructor(userId: number, email: string, password: string) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }
}