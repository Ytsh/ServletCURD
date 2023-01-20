export interface User {
    id: number;
    email:string;
    // username: string;
    // roles: Role;
    token?: string;
    role:string[];
}
