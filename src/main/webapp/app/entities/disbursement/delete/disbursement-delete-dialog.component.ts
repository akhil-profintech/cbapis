import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IDisbursement } from '../disbursement.model';
import { DisbursementService } from '../service/disbursement.service';

@Component({
  standalone: true,
  templateUrl: './disbursement-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class DisbursementDeleteDialogComponent {
  disbursement?: IDisbursement;

  constructor(
    protected disbursementService: DisbursementService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.disbursementService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
