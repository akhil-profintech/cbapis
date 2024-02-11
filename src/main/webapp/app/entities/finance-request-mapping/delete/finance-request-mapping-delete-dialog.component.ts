import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IFinanceRequestMapping } from '../finance-request-mapping.model';
import { FinanceRequestMappingService } from '../service/finance-request-mapping.service';

@Component({
  standalone: true,
  templateUrl: './finance-request-mapping-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FinanceRequestMappingDeleteDialogComponent {
  financeRequestMapping?: IFinanceRequestMapping;

  constructor(
    protected financeRequestMappingService: FinanceRequestMappingService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.financeRequestMappingService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
