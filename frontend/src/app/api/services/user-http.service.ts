import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {User} from "../models/User";
import {API_URL} from "../../config/Constants";

@Injectable({providedIn:'root'})
export class UserHttpService {
  private readonly URL = `${API_URL}/users`;

  constructor(private http:HttpClient) {
  }

  getAll() {
    return this.http.get<User[]>(this.URL);
  }
}
