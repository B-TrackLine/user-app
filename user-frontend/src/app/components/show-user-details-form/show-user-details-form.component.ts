import { Component, Input } from '@angular/core';
import { UserReadModel } from '../../../generated/userservice';
import { TitleCasePipe } from '@angular/common';

@Component({
  selector: 'app-show-user-details-form',
  standalone: true,
  imports: [TitleCasePipe],
  templateUrl: './show-user-details-form.component.html',
  styleUrl: './show-user-details-form.component.css'
})
export class ShowUserDetailsFormComponent {
  @Input() 
  user: UserReadModel | null = null;

}
