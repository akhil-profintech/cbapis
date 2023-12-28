import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IFinancePartner } from '../finance-partner.model';
import { FinancePartnerService } from '../service/finance-partner.service';

@Component({
  standalone: true,
  templateUrl: './finance-partner-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FinancePartnerDeleteDialogComponent {
  financePartner?: IFinancePartner;

  constructor(
    protected financePartnerService: FinancePartnerService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.financePartnerService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
