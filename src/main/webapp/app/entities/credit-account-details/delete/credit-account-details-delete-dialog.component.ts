import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICreditAccountDetails } from '../credit-account-details.model';
import { CreditAccountDetailsService } from '../service/credit-account-details.service';

@Component({
  standalone: true,
  templateUrl: './credit-account-details-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CreditAccountDetailsDeleteDialogComponent {
  creditAccountDetails?: ICreditAccountDetails;

  constructor(
    protected creditAccountDetailsService: CreditAccountDetailsService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.creditAccountDetailsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
