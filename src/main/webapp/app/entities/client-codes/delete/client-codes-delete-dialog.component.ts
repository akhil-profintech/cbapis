import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IClientCodes } from '../client-codes.model';
import { ClientCodesService } from '../service/client-codes.service';

@Component({
  standalone: true,
  templateUrl: './client-codes-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ClientCodesDeleteDialogComponent {
  clientCodes?: IClientCodes;

  constructor(
    protected clientCodesService: ClientCodesService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.clientCodesService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
