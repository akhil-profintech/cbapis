import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICBCREProcess } from '../cbcre-process.model';
import { CBCREProcessService } from '../service/cbcre-process.service';

@Component({
  standalone: true,
  templateUrl: './cbcre-process-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CBCREProcessDeleteDialogComponent {
  cBCREProcess?: ICBCREProcess;

  constructor(
    protected cBCREProcessService: CBCREProcessService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cBCREProcessService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
