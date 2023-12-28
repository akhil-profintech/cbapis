import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IRepayment } from '../repayment.model';
import { RepaymentService } from '../service/repayment.service';

@Component({
  standalone: true,
  templateUrl: './repayment-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class RepaymentDeleteDialogComponent {
  repayment?: IRepayment;

  constructor(
    protected repaymentService: RepaymentService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.repaymentService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
