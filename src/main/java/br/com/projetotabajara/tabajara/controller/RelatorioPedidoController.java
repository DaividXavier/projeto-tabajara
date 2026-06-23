package br.com.projetotabajara.tabajara.controller;
 
import java.io.OutputStream;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
 
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
 
import br.com.projetotabajara.tabajara.entity.Pedido;
import br.com.projetotabajara.tabajara.service.PedidoService;
import jakarta.servlet.http.HttpServletResponse;
 
@Controller
@RequestMapping("/relatorios")
public class RelatorioPedidoController {
 
    @Autowired
    private PedidoService pedidoService;
 
    @Autowired
    private SpringTemplateEngine templateEngine;
 
    @GetMapping("/pedidos")
    public String relatorioPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.findAll();
        model.addAttribute("pedidos", pedidos);
        return "relatorio/relatorioPedidos";
    }
 
    @GetMapping("/pedidos/pdf")
    public void gerarPdf(HttpServletResponse response) throws Exception {
        List<Pedido> pedidos = pedidoService.findAll();
 
        Context context = new Context();
        context.setVariable("pedidos", pedidos);
 
        String html = templateEngine.process("relatorio/relatorioPedidosPdf", context);
 
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio-pedidos.pdf");
 
        try (OutputStream os = response.getOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, null);
            builder.toStream(os);
            builder.run();
        }
    }
}
 
 