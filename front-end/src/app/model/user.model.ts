export class User{

        constructor(
            public id: number,
            public username: string,
            public password: string,
            public role: {
                [x: string]: any;
                id: number,
                rolename: string
            }
        ){}

}