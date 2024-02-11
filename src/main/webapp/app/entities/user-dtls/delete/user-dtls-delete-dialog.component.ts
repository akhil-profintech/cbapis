import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IUserDtls } from '../user-dtls.model';
import { UserDtlsService } from '../service/user-dtls.service';

@Component({
  standalone: true,
  templateUrl: './user-dtls-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class UserDtlsDeleteDialogComponent {
  userDtls?: IUserDtls;

  constructor(
    protected userDtlsService: UserDtlsService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userDtlsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
