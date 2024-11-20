import { AfterViewInit, Component, ElementRef, EventEmitter, Output, ViewChild } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Country, Language } from '../../../generated/userservice';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-user-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-user-form.component.html',
  styleUrl: './add-user-form.component.css'
})
export class AddUserFormComponent implements AfterViewInit {
  protected countries: Country[] = Object.values(Country);
  protected languages: Language[] = Object.values(Language);
  protected formGroup = new FormGroup({ 
    nameControl: new FormControl<string>('', [
      Validators.required
    ]),
    emailControl: new FormControl<string>('', [
      Validators.required,
      Validators.email
    ]),
    countryControl: new FormControl<Country | null>(null, [
      Validators.required
    ]),
    languageControl: new FormControl<Language | null>(null, [
      Validators.required
    ])
  });
  @Output() 
  protected addNewUserEvent = new EventEmitter<[string, string, Country, Language]>();
  @ViewChild('addUserFormModal', {static: false}) 
  protected addUserFormModal!: ElementRef;

  ngAfterViewInit(): void {
    this.addUserFormModal.nativeElement.addEventListener("hidden.bs.modal", () => {
      this.clearInputs();
    });
  }

  protected onSaveClicked(): void {
    this.addNewUserEvent.emit([
      this.formGroup.controls.nameControl.value!,
      this.formGroup.controls.emailControl.value!,
      this.formGroup.controls.countryControl.value!,
      this.formGroup.controls.languageControl.value!]);

    this.clearInputs();
  }

  private clearInputs(): void {
    this.formGroup.controls.nameControl.setValue("");
    this.formGroup.controls.emailControl.setValue("");
    this.formGroup.controls.languageControl.setValue(null);
    this.formGroup.controls.countryControl.setValue(null);
  }

}
