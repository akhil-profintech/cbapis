import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IBeneValidation } from '../bene-validation.model';
import { BeneValidationService } from '../service/bene-validation.service';

@Component({
  standalone: true,
  templateUrl: './bene-validation-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class BeneValidationDeleteDialogComponent {
  beneValidation?: IBeneValidation;

  constructor(
    protected beneValidationService: BeneValidationService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.beneValidationService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
