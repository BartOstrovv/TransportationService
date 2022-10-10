import { Role } from "@api/models/enums/Role";

export class SecurityUser {
  id!: number;
  firstName!: string;
  lastName!: string;
  email!: string;
  created!: string;
  role!: Role;
}
