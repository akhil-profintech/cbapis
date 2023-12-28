import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { FundsTransferDetailComponent } from './funds-transfer-detail.component';

describe('FundsTransfer Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FundsTransferDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: FundsTransferDetailComponent,
              resolve: { fundsTransfer: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(FundsTransferDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load fundsTransfer on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FundsTransferDetailComponent);

      // THEN
      expect(instance.fundsTransfer).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
