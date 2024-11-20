import { Component, OnInit } from '@angular/core';
import { UserTableComponent } from '../../components/user-table/user-table.component';
import { Country, Language, UserReadModel } from '../../../generated/userservice';
import { UserApiService } from '../../services/user-api.service';
import { AddUserFormComponent } from "../../components/add-user-form/add-user-form.component";

@Component({
  selector: 'app-user-administration-page',
  standalone: true,
  imports: [UserTableComponent, AddUserFormComponent],
  templateUrl: './user-administration-page.component.html',
  styleUrl: './user-administration-page.component.css'
})
export class UserAdministrationPageComponent implements OnInit {
  protected users: UserReadModel[] = [];
  protected filterName?: string;
  protected filterEmail?: string;

  constructor(private readonly userApiService: UserApiService) {
    this.userApiService = userApiService;
  }

  ngOnInit(): void {
    this.reload();
  }
  

  protected onNewFilterValues([filterName, filterEmail]: [string, string]) {
    this.filterName = filterName;
    this.filterEmail = filterEmail;
    this.reload();
  }

  protected createNewUser([name, email, country, language]: [string, string, Country, Language]) {
    this.userApiService
    .createNewUser(name, email, country, language)
    .subscribe(() => {
      this.reload();
    });
  }

  protected deleteUser(userId: number): void {
    this.userApiService
    .deleteUser(userId)
    .subscribe(() => {
      this.reload();
    });
  }

  private reload(): void {
    this.userApiService
    .loadUsers(this.filterName, this.filterEmail)
    .subscribe(r => {
      this.users = r;
    });
  }

}
