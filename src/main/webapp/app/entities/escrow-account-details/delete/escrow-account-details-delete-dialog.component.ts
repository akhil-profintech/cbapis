import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IEscrowAccountDetails } from '../escrow-account-details.model';
import { EscrowAccountDetailsService } from '../service/escrow-account-details.service';

@Component({
  standalone: true,
  templateUrl: './escrow-account-details-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class EscrowAccountDetailsDeleteDialogComponent {
  escrowAccountDetails?: IEscrowAccountDetails;

  constructor(
    protected escrowAccountDetailsService: EscrowAccountDetailsService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.escrowAccountDetailsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
