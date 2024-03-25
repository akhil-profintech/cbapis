import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IFundsTransferTransactionDetails } from '../funds-transfer-transaction-details.model';
import { FundsTransferTransactionDetailsService } from '../service/funds-transfer-transaction-details.service';

@Component({
  standalone: true,
  templateUrl: './funds-transfer-transaction-details-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FundsTransferTransactionDetailsDeleteDialogComponent {
  fundsTransferTransactionDetails?: IFundsTransferTransactionDetails;

  constructor(
    protected fundsTransferTransactionDetailsService: FundsTransferTransactionDetailsService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fundsTransferTransactionDetailsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
