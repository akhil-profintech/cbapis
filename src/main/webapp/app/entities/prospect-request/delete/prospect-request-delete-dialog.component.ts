import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IProspectRequest } from '../prospect-request.model';
import { ProspectRequestService } from '../service/prospect-request.service';

@Component({
  standalone: true,
  templateUrl: './prospect-request-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class ProspectRequestDeleteDialogComponent {
  prospectRequest?: IProspectRequest;

  constructor(
    protected prospectRequestService: ProspectRequestService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.prospectRequestService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
