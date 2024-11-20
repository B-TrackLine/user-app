import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Country, Language, UserReadModel, UserService } from '../../generated/userservice';

@Injectable({
  providedIn: 'root'
})
export class UserApiService {

  constructor(private readonly api: UserService) {
    this.api = api;
  }

  public loadUsers(
    filterName?: string, 
    filterEmail?: string): Observable<UserReadModel[]> {
      return this.api
      .listUsers({filterName: filterName, filterEmail: filterEmail})
      .pipe(map(r => r.data!));
  }

  public createNewUser(
    name: string, 
    email: string, 
    country: Country, 
    language: Language): Observable<UserReadModel> {
      return this.api
      .createUser({
        userCreateModel: {name, email, country,language}
      })
      .pipe(map(r => r.data!));
  }

  public deleteUser(userId: number) : Observable<void> {
    return this.api.deleteUser({
      userId: userId
    });
  }
  
}
