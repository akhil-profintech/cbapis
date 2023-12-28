import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ICreditBazaarIntegrator } from '../credit-bazaar-integrator.model';
import { CreditBazaarIntegratorService } from '../service/credit-bazaar-integrator.service';

@Component({
  standalone: true,
  templateUrl: './credit-bazaar-integrator-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class CreditBazaarIntegratorDeleteDialogComponent {
  creditBazaarIntegrator?: ICreditBazaarIntegrator;

  constructor(
    protected creditBazaarIntegratorService: CreditBazaarIntegratorService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.creditBazaarIntegratorService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
