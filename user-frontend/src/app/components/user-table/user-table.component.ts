import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UserReadModel } from '../../../generated/userservice';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ShowUserDetailsFormComponent } from "../show-user-details-form/show-user-details-form.component";

@Component({
  selector: 'app-usertable',
  standalone: true,
  imports: [CommonModule, FormsModule, ShowUserDetailsFormComponent],
  templateUrl: './user-table.component.html',
  styleUrl: './user-table.component.css'
})
export class UserTableComponent {
  @Input() 
  users: UserReadModel[] = [];
  @Output() 
  protected filtersChangedEvent = new EventEmitter<[string, string]>();
  @Output() 
  protected deleteUserEvent = new EventEmitter<number>();

  protected filterName: string = "";
  protected filterEmail: string = "";
  protected showDetailsForUser: UserReadModel | null = null;

  protected onFilterChanged(): void {
    this.filtersChangedEvent.emit([this.filterName, this.filterEmail]);
  }

  protected deleteUser(userToDeleteId: number): void {
    this.deleteUserEvent.emit(userToDeleteId);
  }

  protected showUserDetails(user: UserReadModel): void {
      this.showDetailsForUser = user;
  }
}
