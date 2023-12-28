import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IVANumber } from '../va-number.model';
import { VANumberService } from '../service/va-number.service';

@Component({
  standalone: true,
  templateUrl: './va-number-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class VANumberDeleteDialogComponent {
  vANumber?: IVANumber;

  constructor(
    protected vANumberService: VANumberService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.vANumberService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
