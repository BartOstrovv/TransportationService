import {Injectable} from "@angular/core";
import {API_URL} from "@config/Constants";
import {HttpClient} from "@angular/common/http";
import {Credentials} from "@api/models/Credentials";

@Injectable({providedIn: "root"})
export class AuthHttpService {
  private readonly URL = `${API_URL}/auth`;
  constructor(private http:HttpClient) {
  }

  login(credentials: Credentials) {
      return this.http.post<any>(`${this.URL}/login`, credentials);
  }

  logout() {
    return this.http.post(`${this.URL}/logout`, {responseType: 'text'});
  }
}
