import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IFTTransactionDetails } from '../ft-transaction-details.model';
import { FTTransactionDetailsService } from '../service/ft-transaction-details.service';

@Component({
  standalone: true,
  templateUrl: './ft-transaction-details-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FTTransactionDetailsDeleteDialogComponent {
  fTTransactionDetails?: IFTTransactionDetails;

  constructor(
    protected fTTransactionDetailsService: FTTransactionDetailsService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fTTransactionDetailsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
